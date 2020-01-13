package com.es.opengl.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView glView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // OpenGL用に拡張されたSurfaceViewを用意する
        glView = new GLSurfaceView(this);

        // GLSurfaceViewとRenderクラスを紐付け、描画用の別スレッドを開始する
        glView.setRenderer(GLRender);

        // GLSurfaceViewをActivityに設定し表示する
        setContentView(glView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        glView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        glView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // 描画を担当するクラス
    private static GLSurfaceView.Renderer GLRender = new GLSurfaceView.Renderer() {
        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

        }

        @Override
        public void onSurfaceChanged(GL10 gl10, int w, int h) {
            // 画面の左下の原点0,0から画面の幅・高さのピクセル範囲にかけて描画を許可する
            gl10.glViewport(0, 0, w, h);
        }

        @Override
        public void onDrawFrame(GL10 gl10) {
            // 画面を塗り潰す色情報を指定する
            // RGBA(255, 0, 0, 255)
            gl10.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);

            // OpenGLでは、すべてのピクセルの情報を保存する領域をバッファと呼び
            // ピクセルに関する1ビットの情報を保存するバッファをビットプレーンと呼びます
            // glClearはglClear◯◯というメソッドにより指定された情報を
            // 指定したバッファ領域のビットプレーンに反映します
            gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
        }
    };


}
