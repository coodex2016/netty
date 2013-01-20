/*
 * Copyright 2013 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.channel.rxtx;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultChannelConfig;

import java.util.Map;

import static io.netty.channel.rxtx.RxtxChannelOption.*;

/**
 * Default configuration class for RXTX device connections.
 */
final class DefaultRxtxChannelConfig extends DefaultChannelConfig implements RxtxChannelConfig {

    private volatile int baudrate = 115200;
    private volatile boolean dtr;
    private volatile boolean rts;
    private volatile Stopbits stopbits = Stopbits.STOPBITS_1;
    private volatile Databits databits = Databits.DATABITS_8;
    private volatile Paritybit paritybit = Paritybit.NONE;

    public DefaultRxtxChannelConfig(RxtxChannel channel) {
        super(channel);
    }

    @Override
    public Map<ChannelOption<?>, Object> getOptions() {
        return getOptions(super.getOptions(), BAUD_RATE, DTR, RTS, STOP_BITS, DATA_BITS, PARITY_BIT);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getOption(ChannelOption<T> option) {
        if (option == BAUD_RATE) {
            return (T) Integer.valueOf(getBaudrate());
        }
        if (option == DTR) {
            return (T) Boolean.valueOf(isDtr());
        }
        if (option == RTS) {
            return (T) Boolean.valueOf(isRts());
        }
        if (option == STOP_BITS) {
            return (T) getStopbits();
        }
        if (option == DATA_BITS) {
            return (T) getDatabits();
        }
        if (option == PARITY_BIT) {
            return (T) getParitybit();
        }
        return super.getOption(option);
    }

    @Override
    public <T> boolean setOption(ChannelOption<T> option, T value) {
        validate(option, value);

        if (option == BAUD_RATE) {
            setBaudrate((Integer) value);
        } else if (option == DTR) {
            setDtr((Boolean) value);
        } else if (option == RTS) {
            setRts((Boolean) value);
        } else if (option == STOP_BITS) {
            setStopbits((Stopbits) value);
        } else if (option == DATA_BITS) {
            setDatabits((Databits) value);
        } else if (option == PARITY_BIT) {
            setParitybit((Paritybit) value);
        } else {
            return super.setOption(option, value);
        }
        return true;
    }

    @Override
    public RxtxChannelConfig setBaudrate(final int baudrate) {
        this.baudrate = baudrate;
        return this;
    }

    @Override
    public RxtxChannelConfig setStopbits(final Stopbits stopbits) {
        this.stopbits = stopbits;
        return this;
    }

    @Override
    public RxtxChannelConfig setDatabits(final Databits databits) {
        this.databits = databits;
        return this;
    }

    @Override
    public RxtxChannelConfig setParitybit(final Paritybit paritybit) {
        this.paritybit = paritybit;
        return  this;
    }

    @Override
    public int getBaudrate() {
        return baudrate;
    }

    @Override
    public Stopbits getStopbits() {
        return stopbits;
    }

    @Override
    public Databits getDatabits() {
        return databits;
    }

    @Override
    public Paritybit getParitybit() {
        return paritybit;
    }

    @Override
    public boolean isDtr() {
        return dtr;
    }

    @Override
    public RxtxChannelConfig setDtr(final boolean dtr) {
        this.dtr = dtr;
        return this;
    }

    @Override
    public boolean isRts() {
        return rts;
    }

    @Override
    public RxtxChannelConfig setRts(final boolean rts) {
        this.rts = rts;
        return this;
    }

    @Override
    public RxtxChannelConfig setConnectTimeoutMillis(int connectTimeoutMillis) {
        return (RxtxChannelConfig) super.setConnectTimeoutMillis(connectTimeoutMillis);
    }

    @Override
    public RxtxChannelConfig setWriteSpinCount(int writeSpinCount) {
        return (RxtxChannelConfig) super.setWriteSpinCount(writeSpinCount);
    }

    @Override
    public RxtxChannelConfig setAllocator(ByteBufAllocator allocator) {
        return (RxtxChannelConfig) super.setAllocator(allocator);
    }

    @Override
    public RxtxChannelConfig setAutoRead(boolean autoRead) {
        return (RxtxChannelConfig) super.setAutoRead(autoRead);
    }
}
