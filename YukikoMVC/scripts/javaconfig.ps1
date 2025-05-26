# Base path where SDKs are installed
$javaBase = "C:\Java"

# Check if the directory exists
if (-Not (Test-Path $javaBase)) {
    Write-Host "The directory $javaBase does not exist."
    exit 1
}

# List all folders that start with "jdk"
$jdkDirs = Get-ChildItem -Path $javaBase -Directory | Where-Object { $_.Name -like "jdk*" }

if ($jdkDirs.Count -eq 0) {
    Write-Host "No JDK found in $javaBase."
    exit 1
}

# Sort by version (assuming names like jdk-17, jdk-21 etc)
$latestJdk = $jdkDirs | Sort-Object Name -Descending | Select-Object -First 1
$jdkPath = Join-Path $javaBase $latestJdk.Name

# Set JAVA_HOME and update PATH in the registry (permanent)
[System.Environment]::SetEnvironmentVariable("JAVA_HOME", $jdkPath, [System.EnvironmentVariableTarget]::Machine)

# Remove old PATH entries related to Java
$path = [System.Environment]::GetEnvironmentVariable("Path", [System.EnvironmentVariableTarget]::Machine)
$pathItems = $path -split ';' | Where-Object { ($_ -notlike "*java*") -and ($_ -ne "") }

# Add the new %JAVA_HOME%\bin at the beginning
$newPath = "$jdkPath\bin;" + ($pathItems -join ";")

# Save the new PATH
[System.Environment]::SetEnvironmentVariable("Path", $newPath, [System.EnvironmentVariableTarget]::Machine)

Write-Host "JAVA_HOME updated to: $jdkPath"
Write-Host "Path adjusted to prioritize: $jdkPath\bin"
Write-Host "Restart your terminal for changes to take effect."
