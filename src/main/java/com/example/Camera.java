package com.example;

import org.joml.*;

public class Camera {
    private Vector3f position;
    private Vector3f target;
    private Vector3f up;
    private int width;
    private int height;
    private float near;
    private float far;
    private float aspectRatio;
    private float fovy;
    private float pitch;
    private float yaw;

    public Camera(Vector3f position) {
        this.position = position;                
    }

    public Camera(Vector3f position, Vector3f target, Vector3f up, int width, int height, float near, float far, float fovy, float pitch, float yaw) {
        this.position = position;
        this.target = target;
        this.up = up;
        this.width = width;
        this.height = height;
        this.near = near;
        this.far = far;
        this.fovy = fovy;
        this.pitch = pitch;
        this.yaw = yaw;
        aspectRatio = (float) this.width/this.height;
    }


    public Matrix4f getViewMatrix() {
        Matrix4f view = new Matrix4f().lookAt(
            position, target, up);
        return view;
    }

    public Matrix4f getViewMatrixFPS() {
        Matrix4f view = new Matrix4f();
        view.identity();
        view.rotate((float) java.lang.Math.toRadians(pitch), new Vector3f(1,0,0))
            .rotate((float) java.lang.Math.toRadians(yaw), new Vector3f(0,1,0))
            .translate(-position.x, -position.y, -position.z);
        return view;
    }

    public Matrix4f getProjectionMatrix() {
        Matrix4f proj = new Matrix4f().perspective(fovy, aspectRatio, near, far);
        return proj;
    }

    public void setPosition(Vector3f pos){ this.position = pos;}
    public Vector3f getPosition() { return position; }
    public void move(Vector3f offset) { position.add(offset);}
    public void setTarget(Vector3f target){ this.target = target;}
    public Vector3f getTarget() { return target; }
    public void setUp(Vector3f up){ this.up = up;}
    public Vector3f getUp() { return up; }
    public float getPitch() { return pitch; }
    public void setPitch(float pitch) { this.pitch = pitch; }
    public float getYaw() { return yaw; }
    public void setYaw(float yaw) { this.yaw = yaw; }
}
