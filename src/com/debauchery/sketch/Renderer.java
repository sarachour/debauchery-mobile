package com.debauchery.sketch;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

class Renderer extends Thread {
    private SurfaceHolder holder;
    private SketchPad pad;
    private boolean running = false;

    public Renderer(SurfaceHolder surfaceHolder, SketchPad view) {
        holder = surfaceHolder;
        pad = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    public SurfaceHolder getSurfaceHolder() {
        return holder;
    }

    @Override
    public void run() {
        Canvas canvas = null;
        while (running) {
            try {
                canvas = holder.lockCanvas(null);
                synchronized (holder) {
                    pad.onDraw(canvas);
                }
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}