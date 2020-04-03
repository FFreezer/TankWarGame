package com.example.tankwargame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tankwargame.Enums.MovingDirection;
import com.example.tankwargame.GameEntities.EnemyTank;
import com.example.tankwargame.GameEntities.GameObject;
import com.example.tankwargame.GameEntities.MapEdge;
import com.example.tankwargame.GameEntities.PlayerTank;
import com.example.tankwargame.GameEntities.Tank;
import com.example.tankwargame.GameEntities.Wall;
import com.example.tankwargame.Interfaces.IMovable;

public class GameView extends SurfaceView implements Runnable {

    private Context mContext;
    private Thread mGameThread = null;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;
    private Canvas mCanvas;
    private volatile boolean mRunning;
    private static long fps;
    private PlayerTank playerTank;
    private EnemyTank aiTank;
    private GameControls mControls;
    private int mScreenHeight, mScreenWidth;
    private int mapWidth;
    private int mapHeight;
    private Player mHumanPlayer, mAIPlayer;

    //Constructor
    public GameView(Context context, GameControls controls) {
        super(context);
        this.mContext = context;
        this.mControls = controls;
        mSurfaceHolder = getHolder();
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.wall_color));
        mHumanPlayer = new Player();
        mAIPlayer = new Player();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        /**
         * Method Details
         * Cannot currently get the mWidth and mHeight of the screen via onCreate so must get them as the view inflates.
         * This method is called when the view inflates
         * 1 Tanks are created
         * 2 Walls are created
         *
         **/
        super.onSizeChanged(w, h, oldw, oldh);
        this.mScreenHeight = h;
        this.mScreenWidth = w;
        if(oldw == 0 && oldh == 0){
            initialiseNewRound(w,h);
        }
    }

    /**
     * Attaches listener events to each of the buttons for moving the player tanks
     */
    //Initializers
    @SuppressLint("ClickableViewAccessibility")
    private void initialiseControls(){
        mControls.assignControlsFor(playerTank);
    }

    private void initialiseNewRound(int mapWidth, int mapHeight){
        GameObjectStorage.clearMovableGameObjects();
        GameObjectStorage.clearGameObjects();
//        GameObjectStorage.movableGameObjects.clear();
//        GameObjectStorage.gameObjects.clear();
        initialiseTanks(mapWidth, mapHeight);
        initialiseMapWalls(mapWidth, mapHeight);
        initialiseControls();
    }

    private void initialiseMapWalls(int mapWidth, int mapHeight){
        //Create some randomly placed barriers that are drawn to screen
        for(int numberOfWalls = 0; numberOfWalls < 7; numberOfWalls++){
            GameObject wall = new Wall(mContext, mapWidth, mapHeight);
            GameObjectStorage.addGameObject(wall);
        }
        //Create an invisible wall on each edge of the screen for collision detection
        MapEdge topEdge = new MapEdge(mContext, mapWidth, mapHeight , 5, mapWidth, 0, 5);
        MapEdge rightEdge = new MapEdge(mContext, mapWidth, mapHeight, mapHeight, 5, mapWidth, 0);
        MapEdge bottomEdge = new MapEdge(mContext, mapWidth, mapHeight, 5, mapWidth, 0, mapHeight);
        MapEdge leftEdge = new MapEdge(mContext, mapWidth, mapHeight, mapHeight, 5, 0,0);
        GameObjectStorage.addGameObject(topEdge);
        GameObjectStorage.addGameObject(rightEdge);
        GameObjectStorage.addGameObject(bottomEdge);
        GameObjectStorage.addGameObject(leftEdge);
    }

    private void initialiseTanks(int mapWidth, int mapHeight){
        initialisePlayerTank(mapWidth, mapHeight);
        initialiseAITank(mapWidth, mapHeight);
    }

    private void initialisePlayerTank(int mapWidth, int mapHeight){
        Bitmap mPlayerTankBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ptankup);
        playerTank = new PlayerTank(this, mContext, R.drawable.ptankup, ((mapWidth / 2) - (mPlayerTankBitmap.getWidth() / 2)), (mapHeight - (mPlayerTankBitmap.getHeight() * 2)), MovingDirection.UP);
        GameObjectStorage.addGameObject(0, playerTank);
        GameObjectStorage.addMovableObject(0, playerTank);
        mHumanPlayer.setTank(playerTank);
    }

    private void initialiseAITank(int mapWidth, int mapHeight){
        Bitmap mAITankBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.aitankdown);
        aiTank = new EnemyTank(this, mContext, R.drawable.aitankdown, ((mapWidth / 2) - (mAITankBitmap.getWidth() / 2)), mAITankBitmap.getHeight(), MovingDirection.DOWN, playerTank);
        GameObjectStorage.addMovableObject(1, aiTank);
        GameObjectStorage.addGameObject(1, aiTank);
        mAIPlayer.setTank(aiTank);
    }

    /**
     * @param tank
     *
     * Check to see which tank is to be destroyed.
     * Attempt to negate life from player said tank belongs to
     * If life removal is false then the player has run out of lives and the winner is to be declared
     */
    public void destroyTank(Tank tank){
//        playerTank = (playerTank != null && playerTank.equals(tank)) ? null : playerTank;
        if(playerTank != null && playerTank.equals(tank))
        {
            playerTank = null;
            if(!mHumanPlayer.negateLife())
            {
                declareWinner(mAIPlayer);
            }
            else
            {
                initialiseNewRound(mScreenWidth, mScreenHeight);
            }
        }

        if(aiTank != null && aiTank.equals(tank))
        {
            aiTank = null;
            if(!mAIPlayer.negateLife())
            {
                declareWinner(mHumanPlayer);
            }
            else
            {
                initialiseNewRound(mScreenWidth, mScreenHeight);
            }
        }
//        aiTank = (aiTank != null && aiTank.equals(tank)) ? null : aiTank;
//        initialiseNewRound(mScreenWidth, mScreenHeight);
    }

    private void declareWinner(Player winningPlayer) {
        mRunning = false;
        String text = "DRAW";
        mPaint.setTextSize(80);
        mPaint.setTextAlign(Paint.Align.CENTER);
        if(winningPlayer.equals(mHumanPlayer))
        {
            text = "YOU WIN";
            mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        }
        if(winningPlayer.equals(mAIPlayer))
        {
            text = "YOU LOSE";
            mPaint.setColor(getResources().getColor(R.color.colorAccent));
        }
        this.mCanvas = mSurfaceHolder.lockCanvas();
        this.mCanvas.save(); //2
        this.mCanvas.drawColor(getResources().getColor(R.color.game_background_color));
        this.mCanvas.drawText(text, mScreenWidth / 2, mScreenHeight / 2, mPaint);
        this.mCanvas.restore();
        this.mSurfaceHolder.unlockCanvasAndPost(mCanvas);
    }



    //Game Critical Methods
    @Override
    public void run() {
        while (mRunning) {
            long startFrameTime = System.currentTimeMillis();
            draw();
            update();
//            draw();
            long timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame > 0) {
                //The following 1000 comes from 1000ms in a second
                fps = 1000 / timeThisFrame;
            }
        }
    }

    /**
     * * Draw the newly updated scene and its on screen objects
     *          * 1. Check canvas is valid
     *          * 2. Save the current canvas so we can draw to it without affecting it
     *          * 3. Set background color for the map (or background image)
     *          * 4. Draw tanks
     *          * 5. Draw walls
     *          * 6. Restore canvas
     *          * 7. Unlock canvas and draw
     */
    private void draw() {
        if (mSurfaceHolder.getSurface().isValid()) {
            this.mCanvas = mSurfaceHolder.lockCanvas();
            this.mCanvas.save(); //2
            this.mCanvas.drawColor(getResources().getColor(R.color.game_background_color));
            for(int iterator = 0; iterator < GameObjectStorage.getGameObjectsSize(); iterator++){
                GameObjectStorage.getGameObject(iterator).draw(mCanvas, mPaint);
            }
            this.mCanvas.restore();
            this.mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    //Access Methods
    public static long getFps(){
        return fps;
    }

    public void update() {
        /**
         * Method Details
         * 1. Iterate through all game objects that are able to translatePosition as two 'immovable' objects cannot collide
         * 2. Attempt to translatePosition the object
         * 3. Check against all other game objects for collision using current direction
         * 4. If a collision is detected, translatePosition the object the opposite direction it was moving when the collision happened
         * 5. Method logic is complete
         * */
        for(int iterator = 0; iterator < GameObjectStorage.getMovableObjectsSize(); iterator++){
            IMovable currentMovableObject = GameObjectStorage.getMovableGameObject(iterator);
//            IMovable currentMovableObject = GameObjectStorage.movableGameObjects.get(iterator);
            movableUpdateHelper(currentMovableObject);
        }
//        EnemyTank tank = (EnemyTank) GameObjectStorage.movableGameObjects.get(1);
        if(aiTank != null){
            aiTank.executeStateLogic();
        }
    }

    private void movableUpdateHelper(IMovable gameObject){
        /**
         * Method Details
         * NOTE : This is the helper method ONLY for game objects that can translatePosition like tank shells and tanks
         *
        * */
        if (gameObject.getIsMovingLeft()) {
            gameObject.translatePosition(MovingDirection.LEFT);
        }
        if (gameObject.getIsMovingRight()) {
            gameObject.translatePosition(MovingDirection.RIGHT);
        }
        if (gameObject.getIsMovingUp()) {
            gameObject.translatePosition(MovingDirection.UP);
        }
        if (gameObject.getIsMovingDown()) {
            gameObject.translatePosition(MovingDirection.DOWN);
        }
    }

    //Called in main activity to restart thread
    public void resume() {
        mRunning = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }

    //Called from main activity to suspend threads when game is minimised or rotated
    public void pause() {
        mRunning = false;
        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            Log.d("Error : ", "Attempted pause failed.");
            Log.d("Error Message : ", e.getCause().toString());
        }
    }
}

/*
* TODO
*  Look into Singleton design pattern
* */
