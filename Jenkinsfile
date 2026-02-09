// Selenium tests are executed only when enabled using a Jenkins parameter.

pipeline {
    agent any
    // Define environment variables, including the GitHub token retrieved from Jenkins credentials
    environment {
        GITHUB_TOKEN = credentials('github-token')
    }
    // Define the Maven tool to be used in the pipeline
    tools {
        maven 'Maven_3'
    }
    // Define a boolean parameter to control whether Selenium UI tests should be run
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
                powershell '$len = $env:GITHUB_TOKEN.Length; Write-Host "Token length is $len"'
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
                powershell 'mvn clean test package'
            }
        }

        // SELENIUM STAGE
        stage('UI Tests (Selenium)') {
            when {
                expression { return params.RUN_UI_TESTS }
            }
            steps {
                powershell 'mvn -B verify -DskipUnitTests=true'
            }
        }
    }

    // POST ACTIONS
    post {
        always {
            // Publish JaCoCo code coverage report
            publishHTML(target: [
            reportDir: 'target/site/jacoco',
            reportFiles: 'index.html',
            reportName: 'JaCoCo Code Coverage',
            keepAll: true,
            alwaysLinkToLastBuild: true
        ])
            // Archive screenshots and test results, allowing for empty results to prevent build failure
            archiveArtifacts allowEmptyArchive: true,
                             artifacts: 'target/screenshots/**'
            // Publish JUnit test results, allowing for empty results to prevent build failure
            junit allowEmptyResults: true,
                  testResults: 'target/surefire-reports/*.xml,target/failsafe-reports/*.xml'
        }
    }
}
