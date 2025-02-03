pipeline {
    agent any

    tools {
        maven 'Maven'  // Ensure Maven is installed in Jenkins
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/nex-creator/Api_Test_Automation.git'
            }
        }

        stage('Run API Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                sh 'mvn allure:report'
            }
        }

        stage('Publish Allure Report') {
            steps {
                allure([
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/allure-report/**, api_test_log.txt', fingerprint: true
        }
    }
}
