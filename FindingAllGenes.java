/**
* 
* @author: Amir Armion 
* 
* @version: V.01
* 
*/
public class FindingAllGenes 
{

    public String findGene(String dna)
    {
        int    startIndex = 0;
        int    startCodon = dna.indexOf("ATG");
        int    stopCodon  = 0;
        String gene       = "";
        int    count      = 1;

        if(startCodon == -1)
        {
            return gene;
        }

        while(startIndex < dna.length())
        {
            startCodon = dna.indexOf("ATG", startIndex);

            if(startCodon == -1)
            {
                break;
            }

            int taaIndex = findStopCodon(dna, startCodon, "TAA");
            int tgaIndex = findStopCodon(dna, startCodon, "TGA");
            int tagIndex = findStopCodon(dna, startCodon, "TAG");

            if (taaIndex == -1 && tgaIndex == -1 && tagIndex == -1)
            {
                return gene;
            }
            else if (taaIndex != -1 && tgaIndex != -1 && tagIndex != -1)
            {
                stopCodon = Math.min(taaIndex, Math.min(tgaIndex, tagIndex));
            }
            else if (taaIndex == -1 && tgaIndex != -1 && tagIndex != -1)
            {
                stopCodon = Math.min(tgaIndex, tagIndex);
            }
            else if (taaIndex != -1 && tgaIndex != -1 && tagIndex == -1)
            {
                stopCodon = Math.min(taaIndex, tgaIndex);
            }
            else if (taaIndex != -1 && tgaIndex == -1 && tagIndex != -1)
            {
                stopCodon = Math.min(taaIndex, tagIndex);
            }
            else if (taaIndex == -1 && tgaIndex == -1 && tagIndex != -1)
            {
                stopCodon = tagIndex;
            }
            else if (taaIndex == -1 && tgaIndex != -1 && tagIndex == -1)
            {
                stopCodon = tgaIndex;
            }
            else if (taaIndex != -1 && tgaIndex == -1 && tagIndex == -1)
            {
                stopCodon = taaIndex;
            }

            if ((stopCodon != -1) && (stopCodon <= (dna.length() - 3)))
            {
                gene       = gene + "\nGene " + count + ": " + dna.substring(startCodon, stopCodon + 3);
                startIndex = stopCodon + 3;
                count++;
            }
            else
            {
                break;
            }
        }

        return gene;
    }

    public int findStopCodon(String dna, int startCodon, String stopCodonPattern)
    {
        int stopCodon = dna.indexOf(stopCodonPattern, startCodon + 3);

        while(stopCodon != -1)
        {
            if((stopCodon - startCodon) % 3 == 0)
            {
                return stopCodon;
            }
            else
            {
                stopCodon = dna.indexOf(stopCodonPattern, stopCodon + 1);
            }
        }

        return stopCodon;
    }
}
