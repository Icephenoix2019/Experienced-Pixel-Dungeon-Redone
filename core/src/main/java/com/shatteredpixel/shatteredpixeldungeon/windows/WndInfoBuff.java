/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * Experienced Pixel Dungeon
 * Copyright (C) 2019-2020 Trashbox Bobylev
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.gltextures.SmartTexture;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.Image;
import com.watabou.noosa.TextureFilm;

public class WndInfoBuff extends Window {

	private static final float GAP	= 2;

	private static final int WIDTH = 120;

	private SmartTexture icons;
	private TextureFilm film;

	public WndInfoBuff(Buff buff){
		super();

		IconTitle titlebar = new IconTitle();

		icons = TextureCache.get( Assets.Interfaces.BUFFS_LARGE );
		film = new TextureFilm( icons, 16, 16 );

		Image buffIcon = new Image( icons );
		buffIcon.frame( film.get(buff.icon()) );
		buff.tintIcon(buffIcon);

		titlebar.icon( buffIcon );
		titlebar.label( Messages.titleCase(buff.toString()), Window.TITLE_COLOR );
		titlebar.setRect( 0, 0, WIDTH, 0 );
		add( titlebar );

		RenderedTextBlock txtInfo = PixelScene.renderTextBlock(buff.desc(), 6);
		txtInfo.maxWidth(WIDTH);
		txtInfo.setPos(titlebar.left(), titlebar.bottom() + 2*GAP);
		add( txtInfo );

		float bottom = txtInfo.bottom()+2;

		if(buff instanceof ActionIndicator.Action && ActionIndicator.action != buff && ((ActionIndicator.Action)buff).usable()) {
			RedButton button = new RedButton("Set Active") {
				@Override
				protected void onClick() {
					hide();
					ActionIndicator.setAction((ActionIndicator.Action)buff);
				}
			};
			button.setRect(0,bottom+2,WIDTH,16);
			bottom = button.bottom()+2;
			add(button);
		}

		resize( WIDTH, (int)bottom );
	}
}
