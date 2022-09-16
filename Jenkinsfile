def TARGET_PATH = '/www/server/wison-wpos'
def SOURCE_PATH = './wpos-server/target'
def SHELL_NAME = 'wison-wpos.sh'

pipeline {
    agent any

    tools {
        maven 'maven-3.6.3'
    }

    stages {
        stage('获取构建分支') {
            steps {
                echo "current branch is: ${env.gitlabBranch}"
            }
        }

        stage('构建并重启') {
            when {
                environment name: 'gitlabBranch', value: 'develop'
            }
            steps {
                withEnv(['JENKINS_NODE_COOKIE=dontkillme']) {
                    sh "mvn clean package -U -DskipTests"
                    sh "cp $SOURCE_PATH/*.jar $TARGET_PATH"
                    sh "cp ./$SHELL_NAME $TARGET_PATH"
                    sh "sh $TARGET_PATH/$SHELL_NAME restart"
                }
            }
        }
    }
}