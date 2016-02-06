#include <stdio.h>

int main()
{
	int i=0,j=0;
	for(i=0;i<=10;i++)
	{
		for(j=i;j<=10;j++)
		{
			printf("\t\t(leq n%d n%d)\n",i,j);
		}
	}
}