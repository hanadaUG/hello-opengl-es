package com.es.opengl.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "XXXXX";

    // EGL関連の初期化を代行してくれる
    private GLSurfaceView glView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() E");
        super.onCreate(savedInstanceState);

        // OpenGL用に拡張されたSurfaceViewを用意する
        glView = new GLSurfaceView(this);

        // GLSurfaceViewとRenderクラスを紐付け、描画用の別スレッドを開始する
        glView.setRenderer(GLRender);

        // GLSurfaceViewをActivityに設定し表示する
        setContentView(glView);
        Log.d(TAG, "onCreate() X");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume() E");
        super.onResume();
        glView.onResume();
        Log.d(TAG, "onResume() X");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause() E");
        super.onPause();
        glView.onPause();
        Log.d(TAG, "onPause() X");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() E");
        super.onDestroy();
        Log.d(TAG, "onDestroy() X");
    }

    // 描画を担当するクラス
    private static GLSurfaceView.Renderer GLRender = new GLSurfaceView.Renderer() {
        // 初期化時
        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
            Log.d(TAG, "onSurfaceCreated() E");
            Log.d(TAG, "onSurfaceCreated() X");
        }

        // 画面のサイズが変更された時（端末の回転時などに呼び出される）
        @Override
        public void onSurfaceChanged(GL10 gl10, int w, int h) {
            Log.d(TAG, "onSurfaceChanged() E");
            // 画面の左下の原点0,0から画面の幅・高さのピクセル範囲にかけて描画を許可する
            gl10.glViewport(0, 0, w, h);
            Log.d(TAG, "onSurfaceChanged() X");
        }

        // 毎フレーム呼び出される
        @Override
        public void onDrawFrame(GL10 gl10) {
//            Log.d(TAG, "onDrawFrame() E");
            // 画面を塗り潰す色情報を指定する
            // RGBA(255, 0, 0, 255)
            gl10.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);

            // OpenGLでは、すべてのピクセル情報を保存する領域をバッファと呼びます
            // カラーバッファを塗り潰す（クリアする）
            gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
//            Log.d(TAG, "onDrawFrame() X");
        }
    };
}