package com.termux.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.reflect.Method;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class TermuxInstallerTest {

    @Test
    public void testBuildOpenclawInstallScriptBody_doesNotInstallQqbotPlugin() throws Exception {
        Method method = TermuxInstaller.class.getDeclaredMethod(
            "buildOpenclawInstallScriptBody",
            String.class,
            int.class
        );
        method.setAccessible(true);

        String script = (String) method.invoke(null, "openclaw@latest", 2048);

        assertTrue(script.contains("BOTDROP_STEP:2:START:Installing OpenClaw"));
        assertTrue(script.contains("npm install -g"));
        assertTrue(script.contains("openclaw@2026.9.5"));
        assertTrue(script.contains("touch \"$MARKER\""));
        assertTrue(script.contains("BOTDROP_COMPLETE"));
        assertFalse(script.contains("OFFLINE_QQBOT_TARGET"));
        assertFalse(script.contains("BUNDLED_QQBOT_DIR"));
        assertFalse(script.contains("QQBOT_SOURCE="));
        assertFalse(script.contains("cp -R \"$QQBOT_SOURCE/.\" \"$OFFLINE_QQBOT_TARGET/\""));
    }
}
