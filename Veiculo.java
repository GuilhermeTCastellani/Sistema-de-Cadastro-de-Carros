public class Veiculo {
    private String marca;
    private String modelo;
    private String placa;
    private int ano;
   

    // Construtor default
    public Veiculo() {
    }

    // Construtor completo
    public Veiculo(String marca, String modelo, int ano, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
    }

    // Getters e Setters
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    // toString para imprimir formatado
    @Override
    public String toString() {
        return "Veículo Marca: " + marca +
                ", Modelo: " + modelo +
                ", Ano: " + ano +
                ", Placa: " + placa;
    }
}
