<MainFeatures>
Encode and decode String, URL and file using four algorithms:
Base64< —using apache base64 API
RunLength, Huffman and Lempel Ziv LZ78 <— all codes are write by myself.

=============================if you are using Linux=========================
Use run.sh to check my work.

$> cd to directory
$> sh run.sh $ANT_HOME (your ant home)

============================================================================

<Compile the Code>
cd to the folder, do ant in terminal
OR cd to “src” subfolder, do ant in terminal
<- - - - - - -Don’t move the build.xml between different folder— - - - - - - - ->
$>export PATH=$PATH:$ANT_HOME/bin/
$>ant

<Using the API>
you should copy “encode” folder if you want to move the dir of jar
add the encoder.jar to your Java class path.
export CLASSPATH=.:${Path to encode}/encoder.jar

<All methods returns string. You should export them to a file or post them by your self.>

you can use the api by follow command

EncoderFactory ef = EncoderFactory.getInstance();
Encoder en = ef.getEncoder("Huffman"); //return a Huffman Encoder that can process String.
System.out.println(en.encode(“your string”));
System.out.println(en.decode(en.encode(“your string”)));
en = ef.getFileEncoder("Huffman"); //return a Huffman Encoder that can process Files. 
System.out.println(en.encode(“/PathToFile/filename.extension”));
System.out.println(en.decode(en.encode(“/PathToFile/filename.extension”)));
en = ef.getURLEncoder("Huffman"); //return a Huffman Encoder that can process URLs. 
System.out.println(en.encode(“http://www.google.ie”));
System.out.println(en.decode(en.encode(“http://www.google.ie”)));
