param([string]$JdkHome = $env:JAVA_HOME)
$ErrorActionPreference = 'Stop'
$root = $PSScriptRoot
$windows = [Environment]::OSVersion.Platform -eq 'Win32NT'
if (!$windows -and (uname -s) -ne 'Darwin') { throw 'Build the portable package on Windows or macOS.' }
if (!$JdkHome) { $JdkHome = Split-Path (Split-Path (Get-Command javac).Source) }
$jdk = (Resolve-Path -LiteralPath $JdkHome).Path
if (!(Test-Path -LiteralPath (Join-Path $jdk 'jmods/java.desktop.jmod'))) {
    throw 'Set -JdkHome to the full JDK 22 directory.'
}
$archLine = Select-String '^OS_ARCH="([^"]+)"' -LiteralPath (Join-Path $jdk 'release')
$arch = $archLine.Matches[0].Groups[1].Value.Replace('x86_64','x64').Replace('amd64','x64').Replace('aarch64','arm64')
$platform = if ($windows) { 'Windows' } else { 'macOS' }
$packageName = "Chess-$platform-$arch.zip"
$stage = Join-Path ([IO.Path]::GetTempPath()) ('chess-release-' + [guid]::NewGuid().ToString())
$classes = Join-Path $stage 'classes'
$inputDir = Join-Path $stage 'input'
$imageDir = Join-Path $stage 'image'
$portable = Join-Path $imageDir 'Chess'
$sources = Join-Path $stage 'Chess-source'
$driver = 'sqlite-jdbc-3.54.1.0.jar'

function Run-Jdk([string]$name, [string[]]$toolArgs) {
    & (Join-Path $jdk "bin/$name") @toolArgs
    if ($LASTEXITCODE -ne 0) { throw "$name failed (exit $LASTEXITCODE)." }
}

New-Item -ItemType Directory -Path $classes, "$inputDir/lib", $imageDir, $sources | Out-Null
try {
    Copy-Item -LiteralPath "$root/lib/$driver" -Destination "$inputDir/lib"
    $files = @(Get-ChildItem -LiteralPath "$root/src" -Recurse -Filter '*.java' | ForEach-Object FullName)
    Run-Jdk javac (@('--release', '22', '-encoding', 'UTF-8', '-cp', "$inputDir/lib/$driver", '-d', $classes) + $files)
    Run-Jdk jar @('--create', '--file', "$inputDir/Chess.jar", '--manifest', "$root/resources/META-INF/MANIFEST.MF",
                  '-C', $classes, '.', '-C', "$root/resources", 'pieces', '-C', "$root/resources", 'db')

    # Resolve the save directory from the installed launcher even when opened from another directory.
    $dest = $imageDir
    $dataOption = '-Dchess.dataDir=$APPDIR/../data'
    if (!$windows) {
        $dest = (New-Item -ItemType Directory -Path $portable).FullName
        $dataOption = '-Dchess.dataDir=$APPDIR/../../../data'
    }
    Run-Jdk jpackage @('--type', 'app-image', '--name', 'Chess', '--input', $inputDir, '--dest', $dest,
                       '--main-jar', 'Chess.jar', '--main-class', 'chessGame.MainClass',
                       '--add-modules', 'java.desktop,java.sql', '--java-options', $dataOption)
    if (Test-Path -LiteralPath "$jdk/LICENSE") { Copy-Item -LiteralPath "$jdk/LICENSE" -Destination "$portable/Java-LICENSE.txt" }

    # Only public project inputs belong in the source archive. Saved games and IDE workspace data are local.
    foreach ($item in @('src', 'resources', 'build-release.ps1', 'Chess.iml', '.gitignore')) {
        Copy-Item -LiteralPath (Join-Path $root $item) -Destination $sources -Recurse
    }
    New-Item -ItemType Directory -Path "$sources/lib", "$sources/.idea" | Out-Null
    Copy-Item -LiteralPath "$inputDir/lib/$driver" -Destination "$sources/lib"
    foreach ($item in @('modules.xml', 'misc.xml', 'artifacts', 'libraries')) {
        Copy-Item -LiteralPath "$root/.idea/$item" -Destination "$sources/.idea" -Recurse
    }

    Add-Type -AssemblyName System.IO.Compression.FileSystem
    if ($windows) {
        [IO.Compression.ZipFile]::CreateFromDirectory($portable, "$stage/$packageName", 'Optimal', $true)
    } else {
        & /usr/bin/ditto -c -k --keepParent $portable "$stage/$packageName"
        if ($LASTEXITCODE -ne 0) { throw 'macOS archive creation failed.' }
    }
    [IO.Compression.ZipFile]::CreateFromDirectory($sources, "$stage/Chess-source.zip", 'Optimal', $true)
    $jarDir = Join-Path $root 'out/artifacts/Chess_jar'
    $submission = Join-Path $root 'out/submission'
    New-Item -ItemType Directory -Force -Path "$jarDir/lib", $submission | Out-Null
    Copy-Item -LiteralPath "$inputDir/Chess.jar" -Destination $jarDir -Force
    Copy-Item -LiteralPath "$inputDir/lib/$driver" -Destination "$jarDir/lib" -Force
    Copy-Item -LiteralPath "$stage/$packageName", "$stage/Chess-source.zip" -Destination $submission -Force
    Write-Output "$submission/$packageName"
    Write-Output "$submission/Chess-source.zip"
} finally {
    $tempRoot = [IO.Path]::GetFullPath([IO.Path]::GetTempPath()).TrimEnd([IO.Path]::DirectorySeparatorChar)
    $scratch = (Resolve-Path -LiteralPath $stage).Path
    if ((Split-Path -Parent $scratch) -eq $tempRoot -and (Split-Path -Leaf $scratch) -match '^chess-release-[0-9a-f-]{36}$') {
        Remove-Item -LiteralPath $scratch -Recurse -Force
    }
}
