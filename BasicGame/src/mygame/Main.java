package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.shadow.PointLightShadowFilter;
import com.jme3.shadow.PointLightShadowRenderer;
import com.jme3.system.AppSettings;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.setShowSettings(false);
        AppSettings as = new AppSettings(true);
        as.setTitle("Latihan jMonkey-Chris.");
        app.setSettings(as);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        // adding light,
        PointLight lamp_light = new PointLight();
        lamp_light.setColor(ColorRGBA.Yellow);
        lamp_light.setRadius(40);
        lamp_light.setPosition(new Vector3f(0,20,0));
        rootNode.addLight(lamp_light);
        
        //adding shadow, like... WTF?
        PointLightShadowRenderer plsr = new PointLightShadowRenderer(assetManager, 1024);
        plsr.setLight(lamp_light);
        viewPort.addProcessor(plsr);
        
        PointLightShadowFilter plsf = new PointLightShadowFilter(assetManager, 1024);
        plsf.setLight(lamp_light);
        plsf.setEnabled(true);
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        fpp.addFilter(plsf);
        viewPort.addProcessor(fpp);
        
        /// BEGIN GAME
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        Vector3f v = new Vector3f(2.0f , 1.0f , -3.0f);
        geom.setMaterial(mat);
        geom.move(v);

        geom.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        //latihan rotate?
        Quaternion roll045 = new Quaternion();
        roll045.fromAngleAxis( 45*FastMath.DEG_TO_RAD , Vector3f.UNIT_X
);
        geom.setLocalRotation(roll045);
        rootNode.attachChild(geom);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
