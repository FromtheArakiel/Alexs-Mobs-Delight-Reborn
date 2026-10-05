package dev.arakiel.alexsmobsdelight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.state.BlockState;

public class AcaciaBlossomBlock extends CocoaBlock {
    public AcaciaBlossomBlock(Properties p_51743_) {
        super(p_51743_);
    }

    public boolean canSurvive(BlockState p_51767_, LevelReader p_51768_, BlockPos p_51769_) {
        BlockState blockstate = p_51768_.getBlockState(p_51769_.relative((Direction)p_51767_.getValue(FACING)));
        return blockstate.is(BlockTags.ACACIA_LOGS);
    }
}
