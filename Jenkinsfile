pipeline {
	agent any
	tools {
		maven 'M3'
	}
stages	{
	stage ('build') {
		steps {
			sh 'mvn clean test'
			}
		}
stage('reports') {
	steps {
		script {
		

		publishHTML([
		allowMissing: false, 
		alwaysLinkToLastBuild: false, 
		keepAll: false, 
		reportDir: 'build', 
		reportFiles: 'TestExecutionReport.html', 
		reportName: 'ExtentHTML Report', 
		reportTitles: ''])
		}
	}
}
}
}
