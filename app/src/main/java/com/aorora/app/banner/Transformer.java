package com.aorora.app.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.aorora.app.banner.transformer.AccordionTransformer;
import com.aorora.app.banner.transformer.BackgroundToForegroundTransformer;
import com.aorora.app.banner.transformer.CubeInTransformer;
import com.aorora.app.banner.transformer.CubeOutTransformer;
import com.aorora.app.banner.transformer.DefaultTransformer;
import com.aorora.app.banner.transformer.DepthPageTransformer;
import com.aorora.app.banner.transformer.FlipHorizontalTransformer;
import com.aorora.app.banner.transformer.FlipVerticalTransformer;
import com.aorora.app.banner.transformer.ForegroundToBackgroundTransformer;
import com.aorora.app.banner.transformer.RotateDownTransformer;
import com.aorora.app.banner.transformer.RotateUpTransformer;
import com.aorora.app.banner.transformer.ScaleInOutTransformer;
import com.aorora.app.banner.transformer.StackTransformer;
import com.aorora.app.banner.transformer.TabletTransformer;
import com.aorora.app.banner.transformer.ZoomInTransformer;
import com.aorora.app.banner.transformer.ZoomOutSlideTransformer;
import com.aorora.app.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
