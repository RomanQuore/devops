def call(){

    pipeline {

        agent any
        tools {
            nodejs 'NodeJS18'
        }
        /*triggers {
          pollSCM('* * * * *') // Programa la verificación del repositorio cada minuto
        }*/
        environment {
           PROJECT = "${env.UrlGitHub.replaceAll('.+/(.+)\\.git', '$1')}".toLowerCase(Locale.ROOT)
        } 
        stages {
      
             stage('Fase 2: Construccion de imagen en Docker Desktop') {
                steps {
                    script {
                        def buildimage = new org.devops.lb_buildimagen()
                        buildimage.buildImageDocker("${projectName}")
                    }
                }
                
            }

            stage('Fase 2: Publicar imagen a Docker Hub') {
                steps {
                    script {
                        def publicImage = new org.devops.lb_publicardockerhub()
                        publicImage.publicarimage("${projectName}")
                    }
                }
            }

            stage('Fase 2: Desplegar imagen en Docker') {
                steps {
                    script {
                        def deployImg = new org.devops.lb_deploydocker()
                        deployImg.despliegueContenedor("${projectName}")
                    }
                }
            }
                   
        }
        
    }
}
