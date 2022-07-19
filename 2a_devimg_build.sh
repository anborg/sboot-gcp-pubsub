source ./0_env_gcp.sh
mvn jib:dockerBuild -Djib.dockerClient.executable=$(which podman)

podman image tag $IMG_LOCAL $TAG_ORG  $TAG_GCP
#mvn jib:build
podman images
