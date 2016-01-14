<?php
include('Services/Twilio.php');
require_once('rcon.php');

// All the possible monsters we can spawn.
$monsters = array("zombie","skeleton","spider","creeper");
$x = -97;
$y = 78;
$z = 228;

// Setup config for connection to Minecraft server via RCon
$host = 'localhost';          // Server host name or IP
$port = 25575;                        // Port rcon is listening on
$password = 'twiliocraft';    // rcon.password setting set in server.properties
$timeout = 3;                 // How long to timeout.

// Read the contents of the 'Body' field of the Request.
$body = $_REQUEST['Body'];

// Split body of SMS into an array.
$params = explode(" ", $body);

// Find out if people are saying hello!
if (strtolower($params[0]) == "hi" || strtolower($params[0]) == "hello" || strtolower($params[0]) == "hola" || strtolower($params[0]) == "salut") {
    header("content-type: text/xml");
    echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
?>
<Response>
    <Message>Welcome to the Nick and Jack versus Twilio challenge! To send your monster into battle, text "yourname monster", where monster is a creeper, spider, zombie or skeleton.</Message>
</Response>
<?php
} else if (count($params) != 2) {
  // We got something other than 2 strings seperated by a space, tell user the correct format.

    header("content-type: text/xml");
    echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
?>
<Response>
    <Message>Unable to spawn monster. Please use "yourname monster" Where monster = creeper, zombie, spider or skeleton.</Message>
</Response>
<?php
} else if ( (! in_array($params[1], $monsters)) && (strtolower($params[0]) != "test")) {
  // Don't recognize the mob passed.
    header("content-type: text/xml");
    echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
?>
<Response>
    <Message>Monster not recognized, please specify a creeper, zombie, spider or skeleton.</Message>
</Response>
<?php
} else if (strtolower($params[0]) == "test") {
  // Right, we are just testing mass mob spawning. Let's have some FUN!
    header("content-type: text/xml");
    echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
?>
<Response>
    <Message>Mass attack!! Sending in <?= $params[1] ?> random monsters.</Message>
</Response>
<?php
  for ($i = 1; $i <= $params[1]; $i++) {
    // Setup the Minecraft command.
    $monsternum = rand(0,2);
    $result = "spawnnamedmob Test" . $i . " " . $monsters[$monsternum] . " " . $x . " " . $y . " " . $z;

    $rcon = new Rcon($host, $port, $password, $timeout);

    if ($rcon->connect())
    {
        $rcon->send_command($result);
    }
    sleep(2);
  } // End for loop
} else {
  // Ok we have a valid mob to spawn!! Let's DO this thing.
  // Setup the Minecraft command.
  $result = "spawnnamedmob " . $params[0] . " " . $params[1] . " " . $x . " " . $y . " " . $z;

  $rcon = new Rcon($host, $port, $password, $timeout);

  if ($rcon->connect())
  {
      $rcon->send_command($result);
//      $rcon->send_command("spawnnamedmob Simon spider -97 78 228");
  }

  if (strtolower($params[0]) == "jeff") {
    header("content-type: text/xml");
    echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    ?>
     <Response>
         <Message>JEFF!! just sent <?= $params[0] ?> the <?= $params[1] ?> into battle!!</Message>
    </Response>
<?php
  } else {
    header("content-type: text/xml");
    echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
?>
    <Response>
        <Message>You just sent <?= $params[0] ?> the <?= $params[1] ?> into battle!!</Message>
    </Response>
<?php
  }
} // Close if
?>
