jar cvf TitleCaseConverter.jar .

java -jar TitleCaseConverter.jar 

Create a Launchable JAR File
TC-MANIFEST.MF 
  Main-Class: com.henryjiang.Main

jar cvmf TC-MANIFEST.MF TitleCaseConverter.jar .

============================
Intellij 
Project Structure, Artifacts, Add., Include in project build
Build
java jar TitleCaseConverter.jar