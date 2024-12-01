<%-- 
    Document   : confirmarpedido
    Created on : 21 oct. 2024, 17:40:04
    Author     : jacs2
--%>

<h2>Confirmar Pedido</h2>

<form action="ConfirmarpedidoServlet" method="post">
    <p>Total a pagar: S/. ${carrito.calcularTotal()}</p>

    <label for="metodoPago">M�todo de pago:</label>
    <select id="metodoPago" name="metodoPago" required>
        <option value="efectivo">Pago en Efectivo</option>
        <option value="plin">Pago mediante Plin/Yape</option>
    </select><br><br>

    <input type="submit" value="Finalizar  Pedido">
</form>
