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

package com.shatteredpixel.shatteredpixeldungeon.items.wands;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.watabou.utils.Random;

//for wands that directly damage a target
//wands with AOE effects count here (e.g. fireblast), but wands with indrect damage do not (e.g. venom, transfusion)
public abstract class DamageWand extends Wand{

	public int min(){
		return (int) (min(buffedLvl())*(1+ Dungeon.hero.lvl/150f));
	}

	public abstract int min(int lvl);

	public int max(){
		return (int) (max(buffedLvl())*(1+ Dungeon.hero.lvl/150f));
	}

	public abstract int max(int lvl);

	public int damageRoll(){
		return Random.NormalIntRange(min(), max());
	}

	public int damageRoll(int lvl){
		return Random.NormalIntRange(min(lvl), max(lvl));
	}

	@Override
	public String statsDesc() {
		if (levelKnown)
			return Messages.get(this, "stats_desc", min(), max()) + "\n\n" + Messages.get(Wand.class, "charges", curCharges, maxCharges);
		else
			return Messages.get(this, "stats_desc", min(0), max(0));
	}
}
