// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.plugins.mapwithai;

import static org.openstreetmap.josm.tools.I18n.tr;

import javax.swing.JOptionPane;

import org.openstreetmap.josm.data.Version;
import org.openstreetmap.josm.gui.ConditionalOptionPaneUtil;
import org.openstreetmap.josm.gui.MainApplication;
import org.openstreetmap.josm.spi.preferences.Config;
import org.openstreetmap.josm.tools.OpenBrowser;

/**
 * Prod users to update JOSM (only increment to new compile version)
 *
 * @author Taylor Smock
 */
public final class UpdateProd {
    private UpdateProd() {
        // Hide constructor
    }

    public static void doProd(int nextMinVersion) {
        if (nextMinVersion > Version.getInstance().getVersion()) {
            int doUpdate = ConditionalOptionPaneUtil.showOptionDialog(
                    MapWithAIPlugin.NAME.concat(".ignore_next_version"),
                    MainApplication.getMainFrame(),
                    tr("Please update JOSM -- {0} {1} is the last {0} version to support JOSM {2}",
                            MapWithAIPlugin.NAME, MapWithAIPlugin.getVersionInfo(),
                            Integer.toString(Version.getInstance().getVersion())),
                    tr("{0}: Please update JOSM", MapWithAIPlugin.NAME), JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, new Object[] { tr("Update"), tr("Don''t Update") }, tr("Update"));
            if (doUpdate == 0) {
                OpenBrowser.displayUrl("https://josm.openstreetmap.de");
            }
        } else {
            Config.getPref().put(MapWithAIPlugin.NAME.concat(".ignore_next_version"), null);
            Config.getPref().put(MapWithAIPlugin.NAME.concat(".ignore_next_version.value"), null);
        }

    }
}
