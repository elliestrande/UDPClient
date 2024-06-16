FROM openjdk
COPY UdpClient.java /deployments/
CMD cd /deployments; javac UdpClient.java; java UdpClient