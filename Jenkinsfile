node {
        stage('Clone Repo') {
                git branch: 'main', credentialsId: '4a527ed9-1c3e-49bd-a484-3bf748358370', url: 'https://github.com/mailtopawangupta/FeatureFlagWithSpringBoot.git'
        }
        stage('Maven Build') {
               def mavenHome = tool name:"maven-3.9.2", type: "maven"
               def mavenCMD = "${mavenHome}/bin/mvn"
               bat "${mavenCMD} clean package"
        }
        stage('Code Review'){
                withSonarQubeEnv("SonarQube Scanner Server"){
                    def mavenHome = tool name:"maven-3.9.2", type: "maven"
                    def mavenCMD = "${mavenHome}/bin/mvn"
                    bat "${mavenCMD} sonar:sonar"
                }
        }
    
}
