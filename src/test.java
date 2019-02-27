
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FenetrePrincipale fen = new FenetrePrincipale();
		
		
		
		//Ci-dessous un exemple de declaration propre de tableau a 3 entrèes (de petite taille)
		int [][][] test = 
		{
				{
					{1,1,1},
					{2,2,2},
					{3,3,3}
				},
				{
					{1,1,1},
					{2,2,2},
					{3,3,3}
				},
				{
					{1,1,1},
					{2,2,2},
					{3,3,3}
				},
		};
		
		
		for(int i = 0; i< test.length;i++) {
			for (int j =0; j< test.length;j++) {
				for (int k=0; k< test.length;k++) System.out.print(test[i][j][k]);
				System.out.println();
			}
			System.out.println();
		}
			
	}

}
