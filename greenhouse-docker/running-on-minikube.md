### Minikube

##### Exportar configurações necessárias no ambiente

Antes de criar o minikube, deve-se exportar as seguintes variáveis, de acordo com o [arquivo](https://github.com/kubernetes/minikube/blob/master/docs/vmdriver-none.md):

- *export MINIKUBE_WANTUPDATENOTIFICATION=false*
- *export MINIKUBE_WANTREPORTERRORPROMPT=false*
- *export MINIKUBE_HOME=$HOME*
- *export CHANGE_MINIKUBE_NONE_USER=true*
- *export KUBECONFIG=$HOME/.kube/config*

Para a aplicação ser orquestrada através do Minikube, foram utilizadas as seguintes configurações ao dar *minikube start*:

1. *--vm-driver*=none, para executar em um Ubuntu 18.04 com Docker instalado (configurações mínimas para não precisar de um driver de máquina virtual)
2. *--extra-config*=kubelet.resolv-conf=/run/systemd/resolve/resolv.conf, para que o CoreDNS do minikube não dê problema de CrashLoopBackOff
3. Executar esse comando com permissão *sudo -E*

##### Build das imagens através de Dockerfile

Já que não é utilizada nenhuma VM, as imagens são geradas usando o docker já instalado no Linux, com o seguinte comando:

- _sudo docker build -t microservice-name:tag ./microservice-name_

Opcionalmente, aproveitando o _docker-compose_ já existente:

- _sudo docker-compose build_

##### Subindo as imagens para Minikube através de Kubectl

Com o kubectl integrado ao Minikube, para criar os deployments, basta utilizar o seguinte comando no caso dos microsserviços:

- _kubectl run deployment-name --image=*microservice-name:tag* --port=*configured-port* --image-pull-policy Never_

A configuração de image pull policy _Never_ impedirá que seja feita a busca pela imagem no DockerHub e procure localmente na máquina. O número da porta depende da porta exposta no seu respectivo Dockerfile.

##### Expondo os deployments para usar no browser

Basta usar o seguinte comando: 

-   _kubectl expose deployment deployment-name --type=NodePort_

E, através do Minikube Dashboard (por exemplo), obter o IP do cluster e acessar no browser através do endereço _http://ClusterIP:configured-port_

Será criado um service para o respectivo deployment, possibilitando a interação com o usuário.