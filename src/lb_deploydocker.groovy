package org.devops

def desplieguecontenedor(projectGitName){
    //sh "docker stop ${projectGitName}"
    //sh "docker rm ${projectGitName}"
    sh "docker pull josecorreav/react-test-jenkinsfile"
    sh """" docker run -d --name ${projectGitName} \
    --network=${env.NameNetwork} -p 5174:5174 \
    --user root RomanQuore/${projectGitName}
    """
}
