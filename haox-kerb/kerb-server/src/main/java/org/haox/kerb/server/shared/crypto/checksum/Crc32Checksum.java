package org.haox.kerb.server.shared.crypto.checksum;

import org.haox.kerb.server.shared.crypto.KeyUsage;
import org.haox.kerb.spec.type.common.ChecksumType;

import java.util.zip.CRC32;

class Crc32Checksum implements ChecksumEngine
{
    public ChecksumType checksumType()
    {
        return ChecksumType.CRC32;
    }


    public byte[] calculateChecksum( byte[] data, byte[] key, KeyUsage usage )
    {
        CRC32 crc32 = new CRC32();
        crc32.update( data );

        return int2octet( ( int ) crc32.getValue() );
    }


    private byte[] int2octet( int value )
    {
        byte[] bytes = new byte[4];
        int i, shift;

        for ( i = 0, shift = 24; i < 4; i++, shift -= 8 )
        {
            bytes[i] = ( byte ) ( 0xFF & ( value >> shift ) );
        }

        return bytes;
    }
}
