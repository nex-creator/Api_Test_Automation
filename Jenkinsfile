pipeline {
    agent any

    tools {
        maven 'Maven'  // Ensure Maven is installed in Jenkins
    }

    environment {
        ALLURE_RESULTS_DIR = "target/allure-results"
    }

    stages {
        stage('Checkout Code') {
            steps {
                script {
                    def gitCredentials = credentials('githubtoken')
                    git branch: 'master',  // Change to 'main' if your branch name is 'main'
                        credentialsId: 'githubtoken',
                        url: "https://github.com/nex-creator/Api_Test_Automation.git"
                }
            }
        }

        stage('Build with Maven') {
            steps {
                bat 'mvn clean install'  // Use 'bat' instead of 'sh' for Windows
            }
        }

        stage('Run API Tests') {
            steps {
                bat 'mvn test'  // Use 'bat' instead of 'sh' for Windows
            }
        }

        stage('Generate Allure Report') {
            steps {
                bat 'mvn allure:report'  // Use 'bat' instead of 'sh' for Windows
            }
        }

        stage('Publish Reports') {
            steps {
                publishTestNG testResultsPattern: '**/target/surefire-reports/testng-results.xml'
                archiveArtifacts artifacts: 'target/allure-results/**', fingerprint: true
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed!'
        }
        failure {
            echo 'Pipeline failed. Check logs for details.'
        }
    }
}


