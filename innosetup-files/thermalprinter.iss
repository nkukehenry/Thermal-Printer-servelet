[Setup]
AppName=Thermal Printer Application
AppVersion=1.0
DefaultDirName={pf}\thermalprint
DefaultGroupName=Thermal Printer
OutputDir=Output

[Files]
Source: "thermalprint.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "icon.ico"; DestDir: "{app}"; Flags: ignoreversion

[Icons]
Name: "{commondesktop}\Thermal Printer"; Filename: "{app}\thermalprint.jar"; WorkingDir: "{app}"; IconFilename: "{app}\icon.ico"
Name: "{group}\Thermal Printer"; Filename: "{app}\thermalprint.jar"; WorkingDir: "{app}"; IconFilename: "{app}\icon.ico"

[Run]
Filename: "{app}\thermalprint.jar"; Description: "Launch Thermal Printer Application";Flags: postinstall shellexec

[Registry]
Root: "HKCU"; Subkey: "Software\Microsoft\Windows\CurrentVersion\Run"; ValueType: string; ValueName: "ThermalPrinter"; ValueData: """{app}\thermalprint.jar"""; Flags: uninsdeletevalue