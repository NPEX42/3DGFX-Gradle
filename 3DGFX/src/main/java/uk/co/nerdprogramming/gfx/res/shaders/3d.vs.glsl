#version 330 core
in vec4 a_Position;
in vec2 a_UV;
out vec2 v_UV;
uniform mat4 u_Proj;
void main() {
	gl_Position = u_Proj * a_Position;
	v_UV = a_UV;
}