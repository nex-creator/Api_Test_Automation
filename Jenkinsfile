pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        ALLURE_RESULTS_DIR = "target/allure-results"
        ALLURE_HOME = "C:\\Users\\umesh\\AppData\\Roaming\\npm"
    }

    stages {
        stage('Checkout Code') {
            steps {
                script {
                    def gitCredentials = credentials('githubtoken')
                    git branch: 'master',
                        credentialsId: 'githubtoken',
                        url: "https://github.com/nex-creator/Api_Test_Automation.git"
                }
            }
        }

        stage('Build with Maven') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Run API Tests') {
            steps {
                bat 'mvn clean test -Dallure.results.directory=target/allure-results'
            }
        }

        stage('Check Allure Results') {
            steps {
                script {
                    if (!fileExists('target/allure-results')) {
                        error("ERROR: Allure results directory not found! Ensure tests are executed correctly.")
                    }
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                bat '"%ALLURE_HOME%\\allure.cmd" generate target/allure-results -o target/allure-report'
            }
        }

        stage('Publish Allure Report') {
            steps {
                script {
                    // Archive Allure report so it can be viewed in Jenkins
                    archiveArtifacts artifacts: 'target/allure-report/**', fingerprint: true
                }

                // Display Allure report in Jenkins UI
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'target/allure-results']]
                ])
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

