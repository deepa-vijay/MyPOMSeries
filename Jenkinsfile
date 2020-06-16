pipeline {
  agent any
  stages {

        stage('Build Dev') {
          steps {
            'mvn clean install'
          }
        }

        stage('chrome') {
          steps {
            'mvn test -Denv=qa -Dbrowser=chrome'
          }
        }


    stage('Publish reports') {
      steps {
        script {
        
         // publish html
        		publishHTML([
        		allowMissing: false, 
        		alwaysLinkToLastBuild: false, 
        		keepAll: false, 
        		reportDir: 'build', 
        		reportFiles: 'TestExecutionReport.html', 
        		reportName: 'Extent HTML Report',
        		 reportTitles: ''
        		 ])
          
        }

      }
    }

  }
  tools {
    maven 'M3'
  }
}
