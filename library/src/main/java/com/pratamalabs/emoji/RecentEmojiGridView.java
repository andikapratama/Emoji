package com.pratamalabs.emoji;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pratamalabs.emoji.emoji.Emoji;
import com.pratamalabs.emoji.listeners.OnEmojiClickedListener;

import java.util.ArrayList;
import java.util.Collection;

final class RecentEmojiGridView extends EmojiGridView {
    private RecentEmoji recentEmojis;
    private EmojiArrayAdapter emojiArrayAdapter;

    RecentEmojiGridView(@NonNull final Context context) {
        super(context);
    }

    public RecentEmojiGridView init(@Nullable final OnEmojiClickedListener onEmojiClickedListener, final RecentEmoji recentEmoji) {
        this.recentEmojis = recentEmoji;

        final Collection<Emoji> emojis = recentEmojis.getRecentEmojis();
        emojiArrayAdapter = new EmojiArrayAdapter(getContext(), new ArrayList<>(emojis));
        emojiArrayAdapter.setOnEmojiClickedListener(onEmojiClickedListener);
        this.setAdapter(emojiArrayAdapter);

        return this;
    }

    public void invalidateEmojis() {
        emojiArrayAdapter.updateEmojis(recentEmojis.getRecentEmojis());
    }

    public int numberOfRecentEmojis() {
        return emojiArrayAdapter.getCount();
    }
}
