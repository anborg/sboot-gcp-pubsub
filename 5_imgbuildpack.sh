#brew install buildpacks/tap/pack



https://buildpacks.io/docs/app-developer-guide/building-on-podman/

podman machine init --cpus=2 --disk-size=30 --memory=8192
podman machine set --rootful
podman machine start
#Add the SSH key for the podman VM to your keychain:
ssh-add -k "$HOME/.ssh/podman-machine-default"
podman system connection default podman-machine-default-root
#Configure DOCKER_HOST with the connection information
export DOCKER_HOST="$(podman system connection ls --format="{{.URI}}" | grep root)"


pack config default-builder gcr.io/buildpacks/builder:v1
pack build hello

pack builder inspect <builder-image>

export DOCKER_HOST=ssh://root@localhost:64409