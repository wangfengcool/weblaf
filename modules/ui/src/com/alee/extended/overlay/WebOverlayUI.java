/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.extended.overlay;

import com.alee.api.annotations.NotNull;
import com.alee.api.annotations.Nullable;
import com.alee.managers.style.MarginSupport;
import com.alee.managers.style.PaddingSupport;
import com.alee.managers.style.ShapeSupport;
import com.alee.managers.style.StyleManager;
import com.alee.painter.PainterSupport;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

/**
 * Custom UI for {@link WebOverlay} component.
 *
 * @param <C> component type
 * @author Mikle Garin
 */
public class WebOverlayUI<C extends WebOverlay> extends WOverlayUI<C> implements ShapeSupport, MarginSupport, PaddingSupport
{
    /**
     * Returns an instance of the {@link WebOverlayUI} for the specified component.
     * This tricky method is used by {@link UIManager} to create component UIs when needed.
     *
     * @param c component that will use UI instance
     * @return instance of the {@link WebOverlayUI}
     */
    @NotNull
    public static ComponentUI createUI ( @NotNull final JComponent c )
    {
        return new WebOverlayUI ();
    }

    @Override
    public void installUI ( @NotNull final JComponent component )
    {
        // Installing UI
        super.installUI ( component );

        // Applying skin
        StyleManager.installSkin ( overlay );
    }

    @Override
    public void uninstallUI ( @NotNull final JComponent component )
    {
        // Uninstalling applied skin
        StyleManager.uninstallSkin ( overlay );

        // Uninstalling UI
        super.uninstallUI ( component );
    }

    @NotNull
    @Override
    public Shape getShape ()
    {
        return PainterSupport.getShape ( overlay );
    }

    @Override
    public boolean isShapeDetectionEnabled ()
    {
        return PainterSupport.isShapeDetectionEnabled ( overlay );
    }

    @Override
    public void setShapeDetectionEnabled ( final boolean enabled )
    {
        PainterSupport.setShapeDetectionEnabled ( overlay, enabled );
    }

    @Nullable
    @Override
    public Insets getMargin ()
    {
        return PainterSupport.getMargin ( overlay );
    }

    @Override
    public void setMargin ( @Nullable final Insets margin )
    {
        PainterSupport.setMargin ( overlay, margin );
    }

    @Nullable
    @Override
    public Insets getPadding ()
    {
        return PainterSupport.getPadding ( overlay );
    }

    @Override
    public void setPadding ( @Nullable final Insets padding )
    {
        PainterSupport.setPadding ( overlay, padding );
    }

    @Override
    public boolean contains ( @NotNull final JComponent c, final int x, final int y )
    {
        return PainterSupport.contains ( c, this, x, y );
    }

    @Override
    public int getBaseline ( @NotNull final JComponent c, final int width, final int height )
    {
        return PainterSupport.getBaseline ( c, this, width, height );
    }

    @NotNull
    @Override
    public Component.BaselineResizeBehavior getBaselineResizeBehavior ( @NotNull final JComponent c )
    {
        return PainterSupport.getBaselineResizeBehavior ( c, this );
    }

    @Override
    public void paint ( @NotNull final Graphics g, @NotNull final JComponent c )
    {
        PainterSupport.paint ( g, c, this );
    }

    @Nullable
    @Override
    public Dimension getPreferredSize ( @NotNull final JComponent c )
    {
        return PainterSupport.getPreferredSize ( c );
    }
}