resource "aws_security_group" "allow_ports" {
  name_prefix = "allow_ports_"

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]  # Allow access from anywhere
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_instance" "my_instance" {
  ami           = "ami-0f65a9eac3c203b54"
  instance_type = var.instance_type
  key_name      = var.key_name
  security_groups = [aws_security_group.allow_ports.name]

  user_data = <<-EOF
              #!/bin/bash
              # Onstall Docker
              sudo amazon-linux-extras install docker -y
              sudo service docker start
              sudo usermod -a -G docker ec2-user

              # Pull the Docker image from Docker Hub
              sudo docker pull ${var.docker_image}

              # Run the Docker container
              sudo docker run -d -p 8080:8080 ${var.docker_image}
              EOF

  tags = {
    Name = "MyEC2Instance"
  }
}