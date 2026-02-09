/* groovylint-disable CompileStatic */
// Selenium tests are executed only when enabled using a Jenkins parameter.

pipeline {
    agent any

    environment {
        GITHUB_TOKEN = credentials('github-token')
    }

    tools {
        maven 'Maven_3'
    }

    parameters {
        booleanParam(
            name: 'RUN_UI_TESTS',
            defaultValue: false,
            description: 'Run Selenium UI tests'
        )
    }

    stages {
        // SECURE STEP GITHUB TOKENs
        stage('Secure Step') {
            steps {
                sh 'echo "Token length is ${#GITHUB_TOKEN}"'
            }
        }

        // SCM CHECKOUT
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        // BUILD AND UNIT TESTS
        stage('Build and Test') {
            steps {
                bat 'mvn clean test package'
            }
        }

        // SELENIUM STAGE
        stage('UI Tests (Selenium)') {
            when {
                expression { return params.RUN_UI_TESTS }
            }
            steps {
                bat 'mvn -B verify -DskipUnitTests=true'
            }
        }
    }

    // POST ACTIONS
    post {
        always {
            archiveArtifacts allowEmptyArchive: true,
                             artifacts: 'target/screenshots/**'
            junit allowEmptyResults: true,
                  testResults: 'target/surefire-reports/*.xml,target/failsafe-reports/*.xml'
        }
    }
}
