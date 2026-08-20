$ErrorActionPreference = "Stop"
$ProjectRoot = Split-Path -Parent $PSScriptRoot
$SourceDirectory = Join-Path $ProjectRoot "src\main\java"
$OutputDirectory = Join-Path $PSScriptRoot "bin"
$ActualOutput = Join-Path $PSScriptRoot "ACTUAL.TXT"
$ExpectedOutput = Join-Path $PSScriptRoot "EXPECTED.TXT"

New-Item -ItemType Directory -Force $OutputDirectory | Out-Null
javac -d $OutputDirectory (Join-Path $SourceDirectory "*.java")
Get-Content (Join-Path $PSScriptRoot "input.txt") | java -cp $OutputDirectory Orbit | Set-Content $ActualOutput

$Difference = Compare-Object (Get-Content $ExpectedOutput) (Get-Content $ActualOutput)
if ($Difference) {
    $Difference
    throw "Text UI test failed: ACTUAL.TXT differs from EXPECTED.TXT."
}

Write-Host "Text UI test passed."
