<?php
include("script_php/user.php")
?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>HermesCast</title>
</head>
<body>
<main>
    <h1>Bienvenue sur HermesCast version ALPHA</h1>

    <form method="POST" id="connexion" action="<?= htmlspecialchars($_SERVER['PHP_SELF']); ?>"
          enctype='multipart/form-data'>
        <input type="hidden" name="action" value="connexion">

        <label for='nom' class='form-label'>Nom</label>
        <input type='text' class='input-co' id='nom' name='nom' maxlength='50' required>
        <label for='password' class='form-label'>Password</label>
        <input type="password" class="input-co" id="password" name="password" maxlength="50" required>


    </form>
    <button type='submit' class='btn btn-success' form='conexion'>Enregistrer</button>
</main>
</body>
</html>