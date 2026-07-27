public class NotificadorEmail implements Notificador{
    @Override
    public void enviarNotificacao(String mensagem) {
        System.out.println("Enviando E-mail: " + mensagem);
    }
}
