<?php


if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $action = isset($_POST["action"]) ? $_POST["action"] : ' ';

    switch ($action) {
        case 'connexion':
            print("rest");
    }
}
?>
