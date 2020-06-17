pipeline {

  agent any

  tools {

    maven 'M3'

  }

  stages {

    stage('Build') {

      steps {

        bat 'mvn clean test'

      }

    }

stage('reports') {

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

}