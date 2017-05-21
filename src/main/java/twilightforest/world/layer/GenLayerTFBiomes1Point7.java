package twilightforest.world.layer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import twilightforest.library.BiomeLibrary;


/**
 * Applies the twilight forest biomes to the map
 * 
 * @author Ben
 *
 */
public class GenLayerTFBiomes1Point7 extends GenLayer
{

    private static final int RARE_BIOME_CHANCE = 15;
	protected Biome commonBiomes[] = (new Biome[] {
            BiomeLibrary.twilightForest,
            BiomeLibrary.denseTwilightForest,
            BiomeLibrary.mushrooms,
            BiomeLibrary.oakSavanna,
            BiomeLibrary.fireflyForest
    });
    protected Biome rareBiomes[] = (new Biome[] {
            BiomeLibrary.tfLake,
            BiomeLibrary.deepMushrooms,
            BiomeLibrary.enchantedForest,
            BiomeLibrary.clearing
    });

    public GenLayerTFBiomes1Point7(long l, GenLayer genlayer)
    {
        super(l);
        parent = genlayer;
    }

    public GenLayerTFBiomes1Point7(long l) {
		super(l);
	}

    @Override
	public int[] getInts(int x, int z, int width, int depth)
    {
        int dest[] = IntCache.getIntCache(width * depth);
        for(int dz = 0; dz < depth; dz++)
        {
            for(int dx = 0; dx < width; dx++)
            {
                initChunkSeed(dx + x, dz + z);
                if (nextInt(RARE_BIOME_CHANCE) == 0 ) {
                	// make rare biome
                	dest[dx + dz * width] = Biome.getIdForBiome(rareBiomes[nextInt(rareBiomes.length)]);
                }
                else {
                	// make common biome
                	dest[dx + dz * width] =Biome.getIdForBiome(commonBiomes[nextInt(commonBiomes.length)]);
                }
            }

        }
        
//		for (int i = 0; i < width * depth; i++)
//		{
//			if (dest[i] < 0 || dest[i] > TFBiomeBase.fireSwamp.biomeID)
//			{
//				System.err.printf("Made a bad ID, %d at %d, %d while generating\n", dest[i], x, z);
//			}
//		}

        return dest;
    }
}
