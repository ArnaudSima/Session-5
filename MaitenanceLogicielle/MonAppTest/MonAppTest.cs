using MonApp;

public class CalculatriceTests
{
    [Fact]
    public void Addition_DeDeuxNombres_RetourneLaSomme()
    {
        var calc = new Calculatrice();
        var resultat = calc.Addition(2, 3);
        Assert.Equal(5, resultat);
    }
}
