#### Troubleshooting
1. Library not loaded: /usr/local/opt/openssl/lib/libssl.1.0.0.dylib
> MariaDB 10.2必须使用openssl 1.0.0版本，如果你的Mac正在使用其他版本的openssl。请使用brew install openssl@1.0，
> 然后简单的处理是将openssl 1.0.0版本中的libssl.1.0.0.dylib和libcrypto.1.0.0.dylib拷贝到/usr/local/opt/openssl/lib/目录下即可。

2. 请不要修改flyway版本
> Springboot 3.1以后的版本，flyway的版本会自动升级9.16，,9.16已经不支持MariaDB 10.2。太低的版本又不被Spring 3.1支持，
> 所以经过测试8.5.x是一个合适的版本。
