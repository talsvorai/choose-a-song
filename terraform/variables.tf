variable "instance_type" {
  description = "The type of EC2 instance to launch"
  type        = string
  default     = "t2.micro"
}

variable "key_name" {
  description = "The key pair to use for SSH access"
  type        = string
}

variable "docker_image" {
  description = "The Docker image to run"
  type        = string
  default     = "talsvorai/choose-a-song:artifact-1"
}