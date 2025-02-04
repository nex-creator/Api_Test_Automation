pipeline {
    agent any

    tools {
        maven 'Maven'  // Ensure Maven is installed in Jenkins
    }

    environment {
        ALLURE_RESULTS_DIR = "target/allure-results"
        GITHUB_TOKEN = credentials('githubtoken')  // Fetch GitHub token from Jenkins credentials
    }

    stages {
        stage('Checkout Code') {
            steps {
                script {
                    git branch: 'master',
                        credentialsId: 'githubtoken',
                        url: "https://$GITHUB_TOKEN@github.com/nex-creator/Api_Test_Automation.git"
                }
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run API Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                sh 'mvn allure:report'  // Generates Allure HTML report
            }
        }

        stage('Publish Reports') {
            steps {
                // Publish TestNG results
                publishTestNG testResultsPattern: '**/target/surefire-reports/testng-results.xml'
                
                // Archive Allure results
                archiveArtifacts artifacts: 'target/allure-results/**', fingerprint: true
                
                // Publish Allure reports (requires Allure Plugin in Jenkins)
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

