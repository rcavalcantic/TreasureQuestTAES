<?php
  $id = "NULL";
  $nome  = $_GET['nome'];
  $id_pergunta_final = "2";

  $conexao = mysql_connect('localhost','root','');
  
  mysql_select_db('tesouro',$conexao);

  $sqlsala = "select * from times where nome like '".$nome."'";

  $resultado = mysql_query($sqlsala) or die ("Error :" . mysql_error());

  function teste($resultado){ //se o time existe retorna verdadeiro;
    if (mysql_num_rows($resultado) > 0)
        return true;
    else
        return false;
  }

  $sql = "INSERT INTO times (id, nome, id_pergunta_final) VALUES ('$id', '$nome', '$id_pergunta_final')";

  if (teste($resultado) == false){
    $resultado2 = mysql_query($sql) or die ("Error: " . mysql_error());
      echo "Time cadastrado com sucesso";
  }
  else echo "Time já existe";
?>
